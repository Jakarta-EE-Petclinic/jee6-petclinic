#!/usr/bin/env bash

./mvnw -Psetup > x.txt
#grep -n "Caused by" x.txt

grep -n "ArtifactResolutionException" x.txt
