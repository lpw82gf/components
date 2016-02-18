// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.components.db.oracle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.api.runtime.ComponentRuntimeContainer;
import org.talend.components.api.service.ComponentService;
import org.talend.components.db.oracle.toracleinput.TOracleInputProperties;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.PropertyFactory;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.schema.Schema;
import org.talend.daikon.schema.SchemaElement;
import org.talend.daikon.schema.SchemaFactory;

public class OracleRuntime extends ComponentRuntime {

    private static final Logger LOG = LoggerFactory.getLogger(OracleRuntime.class);

    protected ComponentService componentService;

    protected ComponentRuntimeContainer container;

    protected ComponentProperties properties;

    protected Map<String, SchemaElement> fieldMap;

    protected List<SchemaElement> fieldList;

    public OracleRuntime() {
        
    }

    @Override
    public void setContainer(ComponentRuntimeContainer container) {
        this.container = container;
    }

    @Override
    public ValidationResult connectWithResult(ComponentProperties p) {
        OracleConnectionProperties properties = (OracleConnectionProperties) p;
        ValidationResult vr = new ValidationResult();
        try {
            connect(properties);
        } catch (Exception ex) {
            vr.setMessage(ex.getMessage());
            vr.setStatus(ValidationResult.Result.ERROR);
            return vr;
        }
        return vr;
    }

    @Override
    public void connect(ComponentProperties p) {
        OracleConnectionProperties properties = (OracleConnectionProperties) p;
        final OracleConnectionProperties finalProps = properties;
        
        //TODO do connect
    }

    @Override
    public List<NamedThing> getSchemaNames() {
        List<NamedThing> returnList = new ArrayList<>();
        
        //TODO fill the list
        
        return returnList;
    }

    @Override
    public Schema getSchema(String module) {
        Schema schema = SchemaFactory.newSchema();
        SchemaElement root = SchemaFactory.newSchemaElement("Root");
        schema.setRoot(root);

        boolean loop = false;
        while (loop) {
            SchemaElement child = PropertyFactory.newProperty(null);
            
            //TODO fill the talend schema
            
            root.addChild(child);
        }
        return schema;
    }

    protected void commonBegin(ComponentProperties props) {
        properties = props;

        connect(null);

        Schema schema = null;
        
        fieldMap = schema.getRoot().getChildMap();
        fieldList = schema.getRoot().getChildren();
    }

    @Override
    public void inputBegin(ComponentProperties props) throws Exception {
        TOracleInputProperties sprops = (TOracleInputProperties) props;
        
        commonBegin(props);
    }

    @Override
    public Map<String, Object> inputRow() throws Exception {
        return null;
    }

    @Override
    public void inputEnd() throws Exception {
        
    }

    @Override
    public void outputBegin(ComponentProperties props) throws Exception {
        //do nothing
    }

    @Override
    public void outputMain(Map<String, Object> row) throws Exception {
      //do nothing
    }

    @Override
    public void outputEnd() throws Exception {
      //do nothing
    }

}
