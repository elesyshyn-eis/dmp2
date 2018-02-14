package com.ebsco.eis.dmp2.elasticsearch;

import java.util.Collection;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Component;


@Component
public class QueryFactory {

    /**
     * Match queries accept text/numerics/dates, analyzes them, and constructs a query.
     *
     * @param field          - The field in the index
     * @param criteria       - The search criteria
     * @param allowFuzziness Whether to allow fuzziness
     * @return QueryBuilder
     */
    public QueryBuilder getMatchQuery(String field, Object criteria, boolean allowFuzziness) {
        return getMatchQuery(field, criteria, null, allowFuzziness, null);
    }

    /**
     * Match queries accept text/numerics/dates, analyzes them, and constructs a query.
     *
     * @param field          - The field in the index
     * @param criteria       - The search criteria
     * @param boost          - The amount to boost
     * @param allowFuzziness Whether to allow fuzziness
     * @return QueryBuilder
     */
    public QueryBuilder getMatchQuery(String field, Object criteria, Float boost, boolean allowFuzziness, String minShouldMatch) {
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(field, criteria);

        if (boost != null) {
            matchQuery.boost(boost);
        }

        // If this is a nested field, then wrap query as nested
        if (NestedFields.isNestedField(field)) {
            return QueryBuilders.nestedQuery(NestedFields.getNestedPath(field), matchQuery, ScoreMode.Avg);
        }
        if (!allowFuzziness) {
            matchQuery.fuzzyTranspositions(false);
        }
        if(minShouldMatch != null) {
        	matchQuery.minimumShouldMatch(minShouldMatch);
        }
        return matchQuery;
    }
    

    public NestedQueryBuilder getNestedMatchQuery(String searchField, String boostField, float boostFactor, Object criteria, ScoreMode scoringMode, String[] fieldsToHighlight) {
        
    	// Build the match query
    	MatchQueryBuilder query = QueryBuilders.matchQuery(searchField, criteria);
    	
        // Define the ranking criteria
        FieldValueFactorFunctionBuilder scoreFunction = ScoreFunctionBuilders
            .fieldValueFactorFunction(boostField).factor(boostFactor); // TODO: pass these as variables

        // Combine the matching & ranking criteria
        QueryBuilder functionQuery = QueryBuilders.functionScoreQuery(query, scoreFunction);
    	
    	// Wrap the match query as nested
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(NestedFields.getNestedPath(searchField), functionQuery, scoringMode);
        
        // Create the highlight builder
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        for(String fieldToHighlight : fieldsToHighlight) {
        	highlightBuilder.field(fieldToHighlight);
        }
        
        // Attach the highlight builder to the nested query builder
        nestedQueryBuilder.innerHit(new InnerHitBuilder().setHighlightBuilder(highlightBuilder));
        
        return nestedQueryBuilder;
    }
    
    public QueryBuilder getPhraseQuery(String field, String criteria, int boost, int slop) {
    	
    	MatchPhraseQueryBuilder phraseQuery = QueryBuilders.matchPhraseQuery(field, criteria);
    	phraseQuery.boost(boost);
    	phraseQuery.slop(slop);
    	
    	return phraseQuery;
    }
    
    
    public QueryBuilder getDisMaxQuery(float boost, float tieBreaker, QueryBuilder... queries) {
    	
    	DisMaxQueryBuilder disMaxQuery = QueryBuilders.disMaxQuery();
    	
    	for (QueryBuilder query : queries) {
    		disMaxQuery.add(query);
    	}
    	disMaxQuery.boost(boost);
    	disMaxQuery.tieBreaker(tieBreaker);
    	
    	return disMaxQuery;
    }

    /**
     * The term query finds documents that contain the exact term specified in the inverted index.
     *
     * @param field - The field in the index
     * @return QueryBuilder
     */

    public QueryBuilder getTermQuery(String field, Object criteria) {
        return getTermQuery(field, criteria, null);
    }

    /**
     * The term query finds documents that contain the exact term specified in the inverted index.
     *
     * @param field - The field in the index
     * @param term  - The exact term to match against
     * @param boost - The amount to boost
     * @return QueryBuilder
     */
    public QueryBuilder getTermQuery(String field, Object term, Float boost) {
        TermQueryBuilder termQuery = QueryBuilders.termQuery(field, term);

        if (boost != null) {
            termQuery.boost(boost);
        }

        // If this is a nested field, then wrap query as nested
        if (NestedFields.isNestedField(field)) {
            return QueryBuilders.nestedQuery(NestedFields.getNestedPath(field), termQuery, ScoreMode.Avg);
        }
        return termQuery;
    }

    /** boolean.
     * @return BoolQueryBuilder
     */
    public BoolQueryBuilder getBooleanQuery() {
        return QueryBuilders.boolQuery();
    }

    /**
     * Returns a BoolQueryBuilder with the appropriate minimumShouldMatch property set.
     *
     * @param minimumShouldMatch    minimumShouldMatch
     * @return BoolQueryBuilder
     */
    public BoolQueryBuilder getBooleanQuery(String minimumShouldMatch) {
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.minimumShouldMatch(minimumShouldMatch);
        return bool;
    }

    /**
     * Creates query that selects the documents for the provided ids. Note, this query uses the _uid field.
     *
     * @param documentType - The type of document that the ids are associated with
     * @param ids          - The doc ids
     * @return QueryBuilder
     */
    public QueryBuilder getIdsQuery(String documentType, Collection<String> ids) {
        return QueryBuilders.idsQuery(documentType).addIds(ids.toArray(new String[ids.size()]));
    }
    
    /**
     * Creates a query that selects the documents where the field DOES NOT exist.
     * 
     * @param field - The field to check if it exists
     * @return QueryBuilder - exists query
     */
    public QueryBuilder getFieldDoesNotExistQuery(String field) {
        return QueryBuilders.boolQuery().mustNot(getFieldExistsQuery(field));
    }

    /**
     * Creates a query that selects the documents where the field exists in the document.
     * 
     * @param field - The field to check if it exists
     * @return QueryBuilder - exists query
     */
    public QueryBuilder getFieldExistsQuery(String field) {
        return QueryBuilders.existsQuery(field);
    }

}