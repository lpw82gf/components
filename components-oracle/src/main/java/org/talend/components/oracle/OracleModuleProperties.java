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

import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.properties.presentation.Form;

public class OracleModuleProperties extends ComponentProperties {

    public OracleConnectionProperties connection = new OracleConnectionProperties("connection");
    
    public OracleModuleProperties(String name) {
        super(name);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();
        
        Form mainForm = CommonUtils.addForm(this, Form.MAIN);
        mainForm.addRow(connection.getForm(Form.REFERENCE));
    }

}
