package com.athaydes.spockframework.report.internal
/**
 * @since 1.3.2
 * @author tlos
 */
class HumanReadableSourceCodeFormatter implements com.athaydes.spockframework.report.internal.SourceCodeFormatter {

    private static final String REGEXP_FOR_CAMEL_CASE = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    @Override
    List<String> format(List<String> blocks) {
        blocks.collect { it-> formatBlock(it) }
    }

    String formatBlock(String block) {
        block = replaceChars(block)
        block = slitCamelCaseNamesAndToLowerFromSecond(block)
        block
    }


    private String replaceChars(String block) {
        block = block.replace('(', '')
        block = block.replace(')', '')
        block = block.replace('"', '')
        block = block.replace('\'', '')
        block = block.replace('.', ' ')
        block = block.replace(':', ' ')
        block = block.replace('==', 'is equal to')
        block = block.replace('_', ' ')
        block = block.trim().replaceAll('^def$', '')
        block = block.replace(' = ', ' is ')
        block
    }

    private String slitCamelCaseNamesAndToLowerFromSecond(String block) {
        String [] split = block.split(REGEXP_FOR_CAMEL_CASE)
        if( split.length > 1 ){
           //FIXME: write in groovy way
            for (int i = 1; i < split.length; i++) {
                split[i] = split[i].toLowerCase()
            }
        }
        split.join(" ")
    }

}
