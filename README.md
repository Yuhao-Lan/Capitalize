
# Capitalize Utility

**Project Goals**

- Develop a Java application for replacing strings within a file.

- Get experience with an agile, test-driven process.

**Details**

For this project you must develop, using Java, a simple **command-line** utility called capitalize, which is the same utility for which you developed test frames in the category-partition assignment. For Deliverable 1, you have developed a first implementation of capitalize that makes your initial set of test cases pass. For this second deliverable, you have to modify your implementation to account for a slight update in the specification for capitalize requested by your customer. The updated specification is below, with the changed parts marked in red:

##
Concise (Updated) Specification of utility capitalize

- NAME:
capitalize - capitalizes words in a file.

- SYNOPSIS
capitalize OPT \&lt;filename\&gt;

where OPT can be **zero or more** of
  - -w [delimiters]
  - -m [string]
  - -f
  - -i / -I
  - -o

- COMMAND-LINE ARGUMENTS AND OPTIONS

\&lt;filename\&gt;: the file on which the capitalize operation has to be performed.

-w [\&lt;delimiters\&gt;]: if specified, the utility will capitalize the first character in each _word_, where a word is a sequence of characters terminated by either a [whitespace](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html#isWhitespace(char)), if no delimiters are specified, or any single character in the string of delimiters, otherwise. Note that the end of the file always ends a word.

-m \&lt;string\&gt;: if specified, the utility will change the capitalization of all the sequences of characters that match (in a case insensitive way) the provided string so that the capitalization _matches_ that of string (see examples).

-f: if specified, the capitalize utility will flipthe capitalization of all letters in the file, after applying any other transformation.

-i / -I:  if specified, the capitalize utility will make all letters in the file lower case (for -i) or upper case (for -I) before applying any other transformation.

-o:  if specified, the capitalize utility will output the resulting text to standard output and will leave the file unmodified.

If none of the OPT flags is specified, capitalize capitalizes the first character in each line in the file. Otherwise, the specified flags override the default behavior, as illustrated in the examples below (and in the test file provided for part II).

- NOTES
  - Characters that are not letters are left unchanged.
  - While the last command-line parameter provided is always treated as the filename, OPT flags can be provided in any order (-f is always applied last and -i / -I is always applied first).

- EXAMPLES OF USAGE

**Example 1:**
capitalize file1.txt
capitalizes the first character in each line in the file (if it is a letter).

**Example 2:**
capitalize -w file1.txt
capitalizes the first letter in every [whitespace](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html#isWhitespace(char)) delimited word.

**Example 3:**
capitalize -m CS6300 file1.txt
changes the capitalization of every occurrence of &quot;cs6300&quot;, &quot;Cs6300&quot;, or &quot;cS6300&quot; to &quot;CS6300&quot; (the provided string).

**Example 4:**
capitalize -m AAAA file2.txt
(where the content of the file is &quot;aaaaaa&quot;)
changes the content of the file to &quot;AAAAaa&quot; (i.e., it matches from left to right).

**Example 5:**
capitalize -m &quot;ASAP&quot; -f file1.txt
would replace all instances of &quot;asap&quot;, &quot;Asap&quot;, etc. to &quot;ASAP and then flipthe capitalization of all letters in the file (in particular, all instances of &quot;ASAP&quot; will ultimately be changed to &quot;asap&quot;).

**Example 6:**
capitalize -w &quot;abc&quot; file1.txt
would capitalize the first character in every word (if it is a letter), where a word is any string of characters terminated by &#39;a&#39;, &#39;b&#39;, &#39;c&#39;, or the end of the file.

**Test**  **results:**

Coverage Report:

ALL: classes: 100% (2/2); branches:  74% (130/176); lines: 100% (124/124);

PKG+: edu.gatech.seclass.capitalize classes: 100% (2/2); branches:  74% (130/176); lines: 100% (124/124);

CLS+: Capitalize  branches:  83% (53/64); lines: 100% (52/52);

CLS+: Main  branches:  69% (77/112); lines: 100% (72/72);