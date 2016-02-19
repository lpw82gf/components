package org.talend.components.oracle;

import org.talend.components.common.SchemaProperties;
import org.talend.daikon.schema.Schema;


public class CommonUtils {

    public static Schema getSchema(SchemaProperties schema) {
        return (Schema) schema.schema.getValue();
    }
}
