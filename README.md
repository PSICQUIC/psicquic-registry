# psicquic-registry

This module implements a web page which shows the PSICQUIC services currently registered as well as some information about them (their respective psicquic URLs for data access, the number of interactions stored, etc.). It also provides the means to access different kinds of information about the registry services through a REST API. To see the live registry version, click [here](http://www.ebi.ac.uk/Tools/webservices/psicquic/registry/registry?action=STATUS). For more infomation regarding programmatic access to the PSICQUIC registry, see [here](http://psicquic.github.io/Registry.html).

## Install and run

```
git clone https://github.com/PSICQUIC/psicquic-registry.git
cd psicquic-registry
mvn clean install
mvn jetty:run
```
Then, browse to:
```
http://localhost:9091/psicquic/registry/registry?action=STATUS
```

## Update certificate
TODO: add details here