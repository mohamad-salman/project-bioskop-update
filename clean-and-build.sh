#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."
rm -rf target

mkdir target
mkdir target/classes
mkdir target/mods

echo "--> membuat modul bioskop-domain..."
javac -d target/classes/ms.bioskop.domain \
	$(find bioskop-domain/src/main/java -name "*.java")

jar --create --file=target/mods/ms.bioskop.domain@1.0.jar \
	--module-version=1.0 \
	-C target/classes/ms.bioskop.domain .

echo "--> membuat modul bioskop-cli..."
javac --module-path target/mods \
	-d target/classes/ms.bioskop.cli \
	$(find bioskop-cli/src/main/java -name "*.java")

jar --create --file=target/mods/ms.bioskop.cli@1.0.jar \
	--module-version=1.0 \
	--main-class ms.bioskop.cli.Main \
	-C target/classes/ms.bioskop.cli .
