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
package org.talend.components.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.api.runtime.ComponentRuntimeContainer;
import org.talend.components.api.service.ComponentService;
import org.talend.components.oracle.toracleinput.TOracleInputProperties;
import org.talend.daikon.NamedThing;
import org.talend.daikon.properties.PropertyFactory;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.schema.Schema;
import org.talend.daikon.schema.SchemaElement;
import org.talend.daikon.schema.SchemaFactory;

public class OracleRuntime extends ComponentRuntime {

    protected ComponentService componentService;

    protected ComponentRuntimeContainer container;

    protected ComponentProperties properties;

    protected Map<String, SchemaElement> fieldMap;

    protected List<SchemaElement> fieldList;
    
    //oracle special vars
    private Connection conn = null;
    
    private String dbschema = null;
    
    private ResultSet resultSet = null;

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
    public void connect(ComponentProperties p) throws SQLException {
        properties = p;
        
        OracleConnectionProperties properties = (OracleConnectionProperties) p;
        
        String host = properties.host.getStringValue();
        String port = properties.port.getStringValue();
        String dbname = properties.port.getStringValue();
        String parameters = properties.jdbcparameter.getStringValue();
        
        String username = properties.userPassword.userId.getStringValue();
        String password = properties.userPassword.password.getStringValue();
        
        boolean autocommit = properties.autocommit.getBooleanValue();
        
        StringBuilder url = new StringBuilder();
        url.append("jdbc:oracle:thin:@").append(host).append(":").append(port).append(":").append(dbname);
        if(parameters!=null) {
            url.append("?").append(parameters);
        }
        
        conn = java.sql.DriverManager.getConnection(url.toString(),username,password);
        conn.setAutoCommit(autocommit);
        
        dbschema = properties.dbschema.getStringValue();
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

    protected void commonBegin(ComponentProperties props) throws SQLException {
        connect(props);

        TOracleInputProperties sprops = (TOracleInputProperties) props;
        Schema schema = CommonUtils.getSchema(sprops.schema);
        
        fieldMap = schema.getRoot().getChildMap();
        fieldList = schema.getRoot().getChildren();
    }

    @Override
    public void inputBegin(ComponentProperties props) throws Exception {
        commonBegin(props);
        
        TOracleInputProperties sprops = (TOracleInputProperties) props;
        
        Statement statement = conn.createStatement();
        resultSet = statement.executeQuery(sprops.sql.getStringValue());
    }

    @Override
    public Map<String, Object> inputRow() throws Exception {
        if(!resultSet.next()) {
            return null;
        }
        
        Map<String,Object> row = new HashMap<String,Object>();
        
        for(int i=0;i<fieldList.size();i++) {
            SchemaElement element = fieldList.get(i);
            //TODO fix the type mapping
            row.put(element.getDisplayName(), resultSet.getString(i));
        }
        return row;
    }

    @Override
    public void inputEnd() throws Exception {
        if(conn!=null) {
            conn.close();
        }
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
