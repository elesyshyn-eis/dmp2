package com.ebsco.eis.dmp2.elasticsearch;

import java.util.Collection;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
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
        return getMatchQuery(field, criteria, null, allowFuzziness);
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
    public QueryBuilder getMatchQuery(String field, Object criteria, Float boost, boolean allowFuzziness) {
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
        return matchQuery;
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