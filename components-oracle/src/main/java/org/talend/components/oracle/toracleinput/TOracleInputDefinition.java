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
package org.talend.components.oracle.toracleinput;

import java.io.InputStream;

import org.talend.components.api.Constants;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.oracle.DBInputDefinition;
import org.talend.components.oracle.OracleRuntime;

import aQute.bnd.annotation.component.Component;

/**
 * Component that can connect to a oracle system and get some data out of it.
 */

@Component(name = Constants.COMPONENT_BEAN_PREFIX + TOracleInputDefinition.COMPONENT_NAME, provide = ComponentDefinition.class)
public class TOracleInputDefinition extends DBInputDefinition {

    public static final String COMPONENT_NAME = "tOracleInputNew";

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
        return TOracleInputProperties.class;
    }

    @Override
    public InputStream getMavenPom() {
        return this.getClass().getResourceAsStream("/org/talend/components/oracle/pom.xml");
    }

    @Override
    public ComponentRuntime createRuntime() {
        return new OracleRuntime();
    }

}
