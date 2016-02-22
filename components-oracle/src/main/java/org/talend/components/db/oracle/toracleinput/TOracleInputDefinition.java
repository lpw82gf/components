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
package org.talend.components.db.oracle.toracleinput;

import org.talend.components.api.Constants;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.Connector.ConnectorType;
import org.talend.components.api.component.Trigger;
import org.talend.components.api.component.Trigger.TriggerType;
import org.talend.components.db.oracle.OracleDefinition;

import aQute.bnd.annotation.component.Component;

/**
 * Component that can connect to a oracle system and get some data out of it.
 */

@Component(name = Constants.COMPONENT_BEAN_PREFIX + TOracleInputDefinition.COMPONENT_NAME, provide = ComponentDefinition.class)
public class TOracleInputDefinition extends OracleDefinition {

    public static final String COMPONENT_NAME = "tOracleInputNew"; //$NON-NLS-1$

    public TOracleInputDefinition() {
        super(COMPONENT_NAME);

        setConnectors(new Connector(ConnectorType.FLOW, 0, 1));
        setTriggers(new Trigger(TriggerType.ITERATE, 1, 1), new Trigger(TriggerType.SUBJOB_OK, 1, 0),
                new Trigger(TriggerType.SUBJOB_ERROR, 1, 0));
    }

    @Override
    public boolean isStartable() {
        return true;
    }

    @Override
    public Class<?> getPropertyClass() {
        return TOracleInputProperties.class;
    }

}
