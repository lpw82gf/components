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
package org.talend.components.api.context.osgi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.daikon.i18n.GlobalI18N;
import org.talend.daikon.i18n.I18nMessageProvider;

import aQute.bnd.annotation.component.Reference;

/**
 * GlobalContext implementation for OSGI container
 */
@aQute.bnd.annotation.component.Component(provide = GlobalI18N.class)
public class GlobalContextOsgi extends GlobalI18N {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalContextOsgi.class);

    @Reference
    public void osgiInjectI18nMessagesProvider(I18nMessageProvider messageProvider) {
        i18nMessageProvider = messageProvider;
        LOG.info("Activated i18n messages ({}).", i18nMessageProvider);
    }
}
