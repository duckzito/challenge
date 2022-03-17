FROM mongo

COPY prices.json /docker-entrypoint-initdb.d/

CMD mongoimport --host mongosvc --db btcprice --drop --collection prices --type json --file /docker-entrypoint-initdb.d/prices.json
