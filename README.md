# Java EBCDIC Encodings Support
## Problem
When working with EBCDIC text in Java (e.g. with IBM Db2 for z/OS through a JDBC driver) you might get a java.io.UnsupportedEncodingException for an unsupported EBCDIC encoding

The problem is that the standard JRE environments (Oracle, OpenJDK, etc.) support only some of the EBCDIC encodings (like cp037), but not all of them (e.g. cp1027)

The IBM JRE supports those EBCDIC encodings, but the latest Windows version of the IBM JRE is 8

## Solution
This code registers the missing EBCDIC encodings and uses CP037 to encode/decode them, as if they were aliases of CP037. This should work correctly for standard characters, but may not work for characters specific to those encodings

Another solution could be to extract the EBCDIC encodings from IBM JDK (charsets.jar), decompile them, rename the package name and use them in a CharsetProvider. This solution would correctly support all the encodings

## Download JAR
https://github.com/vadimshchukin/java-ebcdic/releases/download/release/ebcdic.jar

## Build
```bash
javac mainframe/ebcdic/CharsetProvider.java
jar cf ebcdic.jar mainframe META-INF
```
