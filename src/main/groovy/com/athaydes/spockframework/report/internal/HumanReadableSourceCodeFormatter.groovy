package com.athaydes.spockframework.report.internal
/**
 * @since 1.3.2
 * @author tlos
 */
class HumanReadableSourceCodeFormatter implements com.athaydes.spockframework.report.internal.SourceCodeFormatter {

    @Override
    List<String> format(List<String> blocks) {
        blocks.collect { it-> formatBlock(it)}
    }

    String formatBlock(String block) {
        block = block.replace('(','')
        block = block.replace(')','')
        block = block.replace('"','')
        block = block.replace('\'','')
        block = block.replace('.',' ')
        block = block.replace(':',' ')
        block = block.replace('==','is equal to')
        block = block.replace('_',' ')

        block
    }

}
