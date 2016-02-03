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
package org.talend.components.api.wizard;

import org.talend.components.api.TopLevelDefinition;
import org.talend.components.api.properties.ComponentProperties;

/**
 */
public interface ComponentWizardDefinition extends TopLevelDefinition {

    public String getMenuItemName();

    /**
     * Creates a {@link ComponentWizard} based on the this definition.
     *
     * Optionally asks the wizard to remembers a repository location string to allow the wizard to notify the
     * {@link ComponentDesigner} when {@link ComponentProperties} objects are created.
     *
     * @param location the repository location where the {@link ComponentProperties} associated with this wizard are to
     * be stored. See {@link Repository}.
     * @return
     */
    ComponentWizard createWizard(String location);

    /**
     * Creates a {@link ComponentWizard} based on the this definition populated with the specified
     * {@link ComponentProperties}
     *
     * Optionally asks the wizard to remembers a repository location string to allow the wizard to notify the
     * {@link ComponentDesigner} when {@link ComponentProperties} objects are created.
     *
     * @param location the repository location where the {@link ComponentProperties} associated with this wizard are to
     * be stored. See {@link Repository}.
     * @return
     */
    ComponentWizard createWizard(ComponentProperties properties, String location);

    /**
     * This shall be a path relative to the current Wizard definition, ideally is should just be the name of the png
     * image if placed in the same resource folder that the current class. This icon will be computed with the following
     * code
     * 
     * <pre>
     * {@code
     *    this.getClass().getResourceAsStream(getIconPngPath())
     * }
     * </pre>
     * 
     * @see {@link java.lang.Class#getResourceAsStream(String)}
     * @param imageType the type of image requested
     * @return the path to the png resource
     */

    public abstract String getPngImagePath(WizardImageType imageType);

    public boolean supportsProperties(ComponentProperties properties);

    public boolean isTopLevel();

}
