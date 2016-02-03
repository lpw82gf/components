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
package org.talend.components.api.service.testcomponent;

import org.talend.components.api.Constants;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.wizard.AbstractComponentWizardDefintion;
import org.talend.components.api.wizard.ComponentWizard;
import org.talend.components.api.wizard.WizardImageType;
import org.talend.daikon.i18n.GlobalI18N;

import aQute.bnd.annotation.component.Component;

@Component(name = Constants.COMPONENT_WIZARD_BEAN_PREFIX + TestComponentWizardDefinition.COMPONENT_WIZARD_NAME)
public class TestComponentWizardDefinition extends AbstractComponentWizardDefintion {

    public static final String COMPONENT_WIZARD_NAME = "zewizard"; //$NON-NLS-1$

    @Override
    public String getName() {
        return COMPONENT_WIZARD_NAME;
    }

    @Override
    public String getPngImagePath(WizardImageType imageType) {
        return "connectionWizardIcon.png";
    }

    @Override
    public boolean supportsProperties(ComponentProperties properties) {
        if (properties instanceof TestComponentProperties) {
            return true;
        }
        return false;
    }

    @Override
    public ComponentWizard createWizard(String location) {
        return new TestComponentWizard(this, location, GlobalI18N.getI18nMessageProvider());
    }

    @Override
    public ComponentWizard createWizard(ComponentProperties properties, String location) {
        TestComponentWizard wizard = (TestComponentWizard) createWizard(location);
        wizard.props = properties;
        return wizard;
    }

    @Override
    public boolean isTopLevel() {
        return true;
    }

}
