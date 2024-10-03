#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."
rm -rf target

mkdir target
mkdir target/classes
mkdir target/mods

echo "--> membuat modul bioskop-domain..."
javac -d target/classes/ms.refactored.bioskop.domain \
	$(find bioskop-domain/src/main/java -name "*.java")

jar --create --file=target/mods/ms.refactored.bioskop.domain@1.0.jar \
	--module-version=1.0 \
	-C target/classes/ms.refactored.bioskop.domain .

echo "--> membuat modul bioskop-cli..."
javac --module-path target/mods \
	-d target/classes/ms.refactored.bioskop.cli \
	$(find bioskop-cli/src/main/java -name "*.java")

jar --create --file=target/mods/ms.refactored.bioskop.cli@1.0.jar \
	--module-version=1.0 \
	--main-class ms.refactored.bioskop.cli.Main \
	-C target/classes/ms.refactored.bioskop.cli .
