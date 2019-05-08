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

<these are required for jersey jax-rs (install in that order)>

bundle:install mvn:javax.ws.rs/javax.ws.rs-api/2.0
bundle:install mvn:org.glassfish.hk2/hk2-utils/2.5.0-b30
bundle:install mvn:org.glassfish.hk2.external/aopalliance-repackaged/2.5.0-b30		
bundle:install mvn:org.glassfish.hk2/hk2-api/2.5.0-b30
bundle:install mvn:org.glassfish.jersey.bundles.repackaged/jersey-guava/2.25		
bundle:install mvn:org.glassfish.hk2.external/javax.inject/2.5.0-b30		
bundle:install mvn:org.glassfish.hk2/osgi-resource-locator/1.0.1			
bundle:install mvn:javax.validation/validation-api/1.1.0.Final	
bundle:install mvn:org.glassfish.hk2/hk2-locator/2.5.0-b30
bundle:install mvn:org.glassfish.jersey.core/jersey-common/2.25
bundle:install mvn:org.glassfish.jersey.core/jersey-client/2.25
bundle:install mvn:org.glassfish.jersey.core/jersey-server/2.25
bundle:install mvn:org.glassfish.jersey.containers/jersey-container-servlet-core/2.25

for json parsing(install in that order)

bundle:install mvn:org.codehaus.jackson/jackson-core-asl/1.9.13
bundle:install mvn:org.codehaus.jackson/jackson-mapper-asl/1.9.13
bundle:install mvn:org.codehaus.jackson/jackson-jaxrs/1.9.13
bundle:install mvn:org.glassfish.jersey.media/jersey-media-json-jackson/2.7

<these are required for jackson jax-rs >

bundle:install mvn:com.fasterxml.jackson.core/jackson-databind/2.9.8 
bundle:install mvn:com.fasterxml.jackson.core/jackson-core/2.9.8 
bundle:install mvn:com.fasterxml.jackson.core/jackson-annotations/2.9.8
bundle:install mvn:com.fasterxml.jackson.jaxrs/jackson-jaxrs-base/2.9.8 
bundle:install mvn:com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider/2.9.8 		
bundle:install mvn:org.apache.aries.jax.rs/org.apache.aries.jax.rs.jackson/1.0.2