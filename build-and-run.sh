#!/bin/bash

javac @sources -d out
cp -r data/ out/
cd out/
java Main