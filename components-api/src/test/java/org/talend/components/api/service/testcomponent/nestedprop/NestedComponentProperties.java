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
package org.talend.components.api.service.testcomponent.nestedprop;

import static org.talend.daikon.properties.PropertyFactory.*;

import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.properties.Property;
import org.talend.daikon.properties.presentation.Form;

public class NestedComponentProperties extends ComponentProperties {

    public static final String A_GREAT_PROP_NAME = "aGreatProperty"; //$NON-NLS-1$

    public Property aGreatProperty = newProperty(A_GREAT_PROP_NAME);

    public Property anotherProp = newProperty("anotherProp");

    public NestedComponentProperties(String name) {
        super(name);
    }

    @Override
    public void setupLayout() {
        super.setupLayout();
        Form form = Form.create(this, Form.MAIN, "Nested Component");
        form.addRow(aGreatProperty);
        form.addRow(anotherProp);
    }

}