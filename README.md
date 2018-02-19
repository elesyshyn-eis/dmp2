
# DMP 2.0 search POC (Proof of Concept)

Steps to run:

1. Launch as a Spring Boot Application 

2. Go to swagger endpoint: http://localhost:8080/swagger-ui.html

3. Navigate to the Search Controller


Steps to step-up ES locally:

1. Go to etc folder

2. Execute 'create-new-index.sh local' (this will create the mapping in your local ES). 

3. Assign the 'dmp2' alias to your newly created index (use cerebro: https://github.com/lmenezes/cerebro)

4. Copy the data from devqa into your local environment (use elasticdump: https://www.npmjs.com/package/elasticdump)

	elasticdump --input=http://search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com/dmp2 --output=http://localhost:9200/dmp2 --type=data
	
Quick Links: 

	http://search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com/dmp2/_mappings
	http://search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com/dmp2/_search?size=2
	http://search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com/dmp2/_count

	http://localhost:9200/dmp2/_search
	http://localhost:9200/dmp2/_mappings
	http://localhost:9200/dmp2/_count
