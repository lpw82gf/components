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
package org.talend.components.oracle.toracleconnection;

import java.io.InputStream;

import org.talend.components.api.Constants;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.oracle.DBConnectionDefinition;
import org.talend.components.oracle.OracleConnectionProperties;
import org.talend.components.oracle.OracleRuntime;
import org.talend.daikon.properties.ValidationResult;

import aQute.bnd.annotation.component.Component;

@Component(name = Constants.COMPONENT_BEAN_PREFIX
        + TOracleConnectionDefinition.COMPONENT_NAME, provide = ComponentDefinition.class)
public class TOracleConnectionDefinition extends DBConnectionDefinition {

    public static final String COMPONENT_NAME = "tOracleConnectionNew";
    
    @Override
    protected void setComponentName() {
        this.componentName = COMPONENT_NAME;
    }
    
    @Override
    public String[] getFamilies() {
        return new String[] { "Databases/Oracle" };
    }
    
    @Override
    public Class<?> getPropertyClass() {
        return OracleConnectionProperties.class;
    }

    @Override
    public InputStream getMavenPom() {
        return this.getClass().getResourceAsStream("/org/talend/components/oracle/pom.xml");
    }
    
    @Override
    public ComponentRuntime createRuntime() {
        return new OracleRuntime() {

            @Override
            public void inputBegin(ComponentProperties props) throws Exception {
                OracleConnectionProperties properties = (OracleConnectionProperties) props;
                ValidationResult result = connectWithResult(properties);
                if (ValidationResult.Result.ERROR.equals(result.getStatus())) {
                    throw new Exception(result.getMessage());
                }
            }
            
            @Override
            public void inputEnd() throws Exception {
                //do nothing
            }

        };
    }

}
