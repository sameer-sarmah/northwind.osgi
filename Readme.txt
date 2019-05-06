download karaf and extract 
go to the bin folder
open command prompt in bin directory
execute "karaf.bat",this leads to the karaf shell
if you type "help" then you can see all the commands
type "feature:list" command
"feature:install webconsole"
"feature:start webconsole"

For configuring user defined servlets

"feature:install http"
"feature:start http"
"feature:install http-whiteboard"
"feature:start http-whiteboard"

add apache http client and gson bundles

bundle:install mvn:org.apache.httpcomponents/httpcore-osgi/4.3.2
bundle:install mvn:org.apache.httpcomponents/httpclient-osgi/4.3.4
bundle:install mvn:com.google.code.gson/gson/2.3.1


Adding JPA 


feature:install hibernate
feature:start hibernate
feature:install hibernate-orm
feature:start hibernate-orm
feature:install jpa
feature:start jpa
bundle:install mvn:mysql/mysql-connector-java/5.1.35
bundle:install mvn:org.hibernate.javax.persistence/hibernate-jpa-2.1-api/1.0.0.Final
bundle:install mvn:org.apache.aries.jpa.javax.persistence/javax.persistence_2.1/2.7.0
bundle:install mvn:org.javassist/javassist/3.20.0-GA