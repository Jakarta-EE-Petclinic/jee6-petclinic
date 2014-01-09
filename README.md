jee6-petclinic
==============

JSF with Java EE 6 Version of Spring Petclinic

install JBoss
-------------

install JBoss 7.1.1.Final from http://www.jboss.org/jbossas/downloads/ to e.g. /Users/tw/srv/jboss-as-7.1.1.Final/

start JBoss by: cd /Users/tw/srv/jboss-as-7.1.1.Final/bin ; ./standalone.sh

build and run
-------------

build project with: mvn clean install

copy war to deployment directory of jboss: cp -f target/jee6-petclinic-1.0-SNAPSHOT.war /Users/tw/srv/jboss-as-7.1.1.Final/standalone/deployments/

open url in browser: http://localhost:8080/jee6-petclinic-1.0-SNAPSHOT/

First Steps
-----------

add some PetTypes like dog,cat,mouse,...

add some Specialties for Vetinarians like dentist, anesthetist, radiology,...

add a Vetinarian

add an Owner, add him am a Pet and his Pet a visit.

visit Spring Petclinic
----------------------
https://github.com/spring-projects/spring-petclinic
