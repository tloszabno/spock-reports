package com.athaydes.spockframework.report.internal
/**
 * @since 1.3.2
 * @author tlos
 */
class HumanReadableSourceCodeFormatter implements com.athaydes.spockframework.report.internal.SourceCodeFormatter {

    private static final String REGEXP_FOR_CAMEL_CASE = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    private static final String EMPTY = ""
    private static final String SPACE = " "

    private static final Map<String, String> REPLACEMENTS = [
            "("      : EMPTY,
            ")"      : EMPTY,
            "'"      : EMPTY,
            "."      : SPACE,
            ":"      : SPACE,
            "=="     : "is equal to",
            "== null": "is not set",
            "!= null": "is set",
            "_"      : SPACE,
            " = "    : " is "
    ]


    @Override
    List<String> format(List<String> blocks) {
        blocks.collect { it -> formatBlock(it) }
    }

    String formatBlock(String block) {
        block = replaceChars(block)
        block = slitCamelCaseNamesAndToLowerFromSecond(block)
        block = block.capitalize()
        block
    }


    private String replaceChars(String block) {
        for (replacement in REPLACEMENTS) {
            block = block.replace(replacement.key, replacement.value)
        }
        block
    }

    private String slitCamelCaseNamesAndToLowerFromSecond(String block) {
        String[] split = block.split(REGEXP_FOR_CAMEL_CASE)
        if (split.length > 1) {
            split.collect({ it -> it.toLowerCase() })
                    .join(" ")
        } else {
            block
        }
    }

}
