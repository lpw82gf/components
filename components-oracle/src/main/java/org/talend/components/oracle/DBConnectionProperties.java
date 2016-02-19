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

import static org.talend.daikon.properties.PropertyFactory.newBoolean;
import static org.talend.daikon.properties.PropertyFactory.newString;

import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.UserPasswordProperties;
import org.talend.daikon.properties.Property;
import org.talend.daikon.properties.ValidationResult;
import org.talend.daikon.properties.presentation.Form;

public class DBConnectionProperties extends ComponentProperties {
    
    //basic setting start
    public Property host = newString("host");
    public Property port = newString("port");
    
    public Property database = newString("database");
    public Property dbschema = newString("dbschema");

    private final String userpassword = "userPassword";
    public UserPasswordProperties userPassword = new UserPasswordProperties(userpassword);
    
    public Property jdbcparameter = newString("jdbcparameter");

    //advanced setting start
    public Property autocommit = newBoolean("autocommit");
    
    public DBConnectionProperties(String name) {
        super(name);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();

        Form mainForm = new Form(this, Form.MAIN);
        
        mainForm.addRow(host);
        mainForm.addColumn(port);
        
        mainForm.addRow(database);
        mainForm.addColumn(dbschema);
        
        mainForm.addRow(userPassword.getForm(Form.MAIN));
        
        mainForm.addRow(jdbcparameter);

        Form advancedForm = new Form(this, Form.ADVANCED);
        advancedForm.addRow(autocommit);
    }

    public ValidationResult validateTestConnection() throws Exception {
        OracleRuntime conn = new OracleRuntime();
        return conn.connectWithResult(this);
    }

    @Override
    public void refreshLayout(Form form) {
        super.refreshLayout(form);
    }

}
