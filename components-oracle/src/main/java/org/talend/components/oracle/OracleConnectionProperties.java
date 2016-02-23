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

import org.talend.components.oracle.toracleconnection.TOracleConnectionDefinition;


public class OracleConnectionProperties extends DBConnectionProperties {

    public OracleConnectionProperties(String name) {
        super(name);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();
    }
    
    protected String getReferencedComponentName() {
        return TOracleConnectionDefinition.COMPONENT_NAME;
    }

}
