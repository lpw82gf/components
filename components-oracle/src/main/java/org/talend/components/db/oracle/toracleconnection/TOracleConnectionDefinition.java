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
package org.talend.components.db.oracle.toracleconnection;

import org.talend.components.api.Constants;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.Connector.ConnectorType;
import org.talend.components.api.component.Trigger;
import org.talend.components.api.component.Trigger.TriggerType;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.db.oracle.OracleConnectionProperties;
import org.talend.components.db.oracle.OracleDefinition;
import org.talend.components.db.oracle.OracleRuntime;
import org.talend.daikon.properties.ValidationResult;

import aQute.bnd.annotation.component.Component;

@Component(name = Constants.COMPONENT_BEAN_PREFIX
        + TOracleConnectionDefinition.COMPONENT_NAME, provide = ComponentDefinition.class)
public class TOracleConnectionDefinition extends OracleDefinition {

    public static final String COMPONENT_NAME = "tOracleConnectionNew"; //$NON-NLS-1$

    public TOracleConnectionDefinition() {
        super(COMPONENT_NAME);
        setConnectors(new Connector(ConnectorType.FLOW, 0, 0));
        setTriggers(new Trigger(TriggerType.ITERATE, 1, 0), new Trigger(TriggerType.SUBJOB_OK, 1, 0),
                new Trigger(TriggerType.SUBJOB_ERROR, 1, 0));
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

        };
    }

    @Override
    public boolean isStartable() {
        return true;
    }

    @Override
    public Class<?> getPropertyClass() {
        return OracleConnectionProperties.class;
    }

}
