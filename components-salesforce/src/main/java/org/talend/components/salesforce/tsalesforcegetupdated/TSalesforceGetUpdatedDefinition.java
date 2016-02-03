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
package org.talend.components.salesforce.tsalesforcegetupdated;

import org.talend.components.api.Constants;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.Connector.ConnectorType;
import org.talend.components.api.component.Trigger;
import org.talend.components.api.component.Trigger.TriggerType;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.runtime.ComponentRuntime;
import org.talend.components.salesforce.SalesforceDefinition;
import org.talend.components.salesforce.SalesforceGetDeletedUpdatedProperties;
import org.talend.components.salesforce.SalesforceRuntime;

import aQute.bnd.annotation.component.Component;

@Component(name = Constants.COMPONENT_BEAN_PREFIX
        + TSalesforceGetUpdatedDefinition.COMPONENT_NAME, provide = ComponentDefinition.class)
public class TSalesforceGetUpdatedDefinition extends SalesforceDefinition {

    public static final String COMPONENT_NAME = "tSalesforceGetUpdatedNew"; //$NON-NLS-1$

    public TSalesforceGetUpdatedDefinition() {
        super(COMPONENT_NAME);

        setConnectors(new Connector(ConnectorType.FLOW, 0, 1));
        setTriggers(new Trigger(TriggerType.SUBJOB_OK, 1, 0), new Trigger(TriggerType.SUBJOB_ERROR, 1, 0));
    }

    @Override
    public ComponentRuntime createRuntime() {
        return new SalesforceRuntime() {

            @Override
            public void inputBegin(ComponentProperties props) throws Exception {
            }

        };
    }

    @Override
    public boolean isStartable() {
        return true;
    }

    @Override
    public String getPartitioning() {
        return AUTO;
    }

    @Override
    public Class<?> getPropertyClass() {
        return SalesforceGetDeletedUpdatedProperties.class;
    }

}
