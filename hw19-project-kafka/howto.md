# Запуск в IDE

Настройка профиля:
- Choosing Run | Edit Configurations...
- Go to the Configuration tab
- Expand the Environment section to reveal VM options
- -Dspring.profiles.active=dev

Профиль для config-server: -Dspring.profiles.active=native 

Настройка keycloak:
- копируем k8s-config/volume/sso/h2/keycloakdb.mv.db в /opt/keycloak/data/h2/
- sudo ./kc.sh start-dev --http-port=80 --hostname-debug=true --metrics-enabled=true
- проверяем [keycloak](http://localhost/) admin/admin

Настройки web-клиента:
- routes-настройки web для gateway-server переносим в booklibrary
- проект web-client переносим в booklibrary/resources/static

Kafka:
- у zookeeper меняем порт admin.serverPort=7080
- curl -X POST --data-binary "@booklibrary.json" -H "Content-Type: application/json" http://localhost:8083/connectors | jq

Postgres:
- ОШИБКА: изменение в таблице "databasechangelog" невозможно, так как в ней отсутствует идентификатор реплики, то выполнить: 
  - ALTER TABLE booklibrary.databasechangelog REPLICA IDENTITY FULL;
  - ALTER TABLE booklibrary.databasechangelog REPLICA IDENTITY md5sum;
  - [Подробнее](https://forum.liquibase.org/t/upgrading-liquibase-version-from-4-5-0-to-4-23-1/8699)


Стартуем проекты:
- config-server
- discovery-server
- gateway-server
- auth-microservice
- booklibrary-microservice
- notification-microservice
