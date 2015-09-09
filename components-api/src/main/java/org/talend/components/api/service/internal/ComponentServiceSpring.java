// ============================================================================
//
// copyright (c) 2006-2015 talend inc. - www.talend.com
//
// this source code is available under agreement available at
// %installdir%\features\org.talend.rcp.branding.%productname%\%productname%license.txt
//
// you should have received a copy of the agreement
// along with this program; if not, write to talend sa
// 9 rue pages 92150 suresnes, france
//
// ============================================================================
package org.talend.components.api.service.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentDefinition;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.service.ComponentService;
import org.talend.components.api.wizard.ComponentWizardDefinition;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * This is a spring only class that is instanciated by the spring framework. It delegates all its calls to the
 * ComponentServiceImpl delegate create in it's constructor. This delegate uses a Component regitry implementation
 * specific to spring.
 */
@RestController
@Api(value = "components", basePath = ComponentServiceSpring.BASE_PATH, description = "Component services")
@Service
public class ComponentServiceSpring implements ComponentService {

    static final String      BASE_PATH = "/components"; //$NON-NLS-1$

    private ComponentService componentServiceDelegate;

    @Autowired
    public ComponentServiceSpring(final ApplicationContext context) {
        this.componentServiceDelegate = new ComponentServiceImpl(new ComponentRegistry() {

            @Override
            public Map<String, ComponentDefinition> getComponents() {
                return context.getBeansOfType(ComponentDefinition.class);
            }

            @Override
            public Map<String, ComponentWizardDefinition> getComponentWizards() {
                return context.getBeansOfType(ComponentWizardDefinition.class);
            }
        });
    }

    @Override
    @RequestMapping(value = BASE_PATH
            + "/properties/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComponentProperties getComponentProperties(
            @PathVariable(value = "name") @ApiParam(name = "name", value = "name of the components") String name) {
        return componentServiceDelegate.getComponentProperties(name);
    }

    @Override
    @RequestMapping(value = BASE_PATH
            + "/properties/validate/{propName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComponentProperties validateProperty(
            @PathVariable(value = "propName") @ApiParam(name = "propName", value = "Name of property") String propName,
            @ApiParam(name = "properties", value = "Component properties") @RequestBody ComponentProperties properties)
                    throws Throwable {
        componentServiceDelegate.validateProperty(propName, properties);
        return properties;
    }

    @Override
    @RequestMapping(value = BASE_PATH
            + "/properties/before/{propName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComponentProperties beforeProperty(
            @PathVariable(value = "propName") @ApiParam(name = "propName", value = "Name of property") String propName,
            @ApiParam(name = "properties", value = "Component properties") @RequestBody ComponentProperties properties)
                    throws Throwable {
        componentServiceDelegate.beforeProperty(propName, properties);
        return properties;
    }

    @Override
    @RequestMapping(value = BASE_PATH
            + "/properties/after/{propName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComponentProperties afterProperty(
            @PathVariable(value = "propName") @ApiParam(name = "propName", value = "Name of property") String propName,
            @ApiParam(name = "properties", value = "Component properties") @RequestBody ComponentProperties properties)
                    throws Throwable {
        componentServiceDelegate.afterProperty(propName, properties);
        return properties;
    }

    @Override
    @RequestMapping(value = BASE_PATH + "/names", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Set<String> getAllComponentNames() {
        return componentServiceDelegate.getAllComponentNames();
    }

    @Override
    @RequestMapping(value = BASE_PATH + "/definitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Set<ComponentDefinition> getAllComponents() {
        return componentServiceDelegate.getAllComponents();
    }

    @RequestMapping(value = BASE_PATH
            + "/wizards/definitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Set<ComponentWizardDefinition> getTopLevelComponentWizards() {
        return componentServiceDelegate.getTopLevelComponentWizards();
    }

    @Override
    // this cannot be used as is as a rest api so see getWizardImageRest.
    public InputStream getWizardPngImage(String wizardName) {
        return componentServiceDelegate.getWizardPngImage(wizardName);
    }

    @RequestMapping(value = BASE_PATH + "/wizards/icon/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Return the icon related to the wizard", notes = "return the png image related to the wizard parameter.")
    public void getWizardImageRest(@PathVariable(value = "name") @ApiParam(name = "name", value = "Name of wizard") String name,
            final HttpServletResponse response) {
        try {
            IOUtils.copy(getWizardPngImage(name), response.getOutputStream());
        } catch (IOException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    // this cannot be used as is as a rest api so see getWizardPngIconRest.
    public InputStream getComponentPngImage(String componentName) {
        return componentServiceDelegate.getComponentPngImage(componentName);
    }

    @RequestMapping(value = BASE_PATH + "/icon/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Return the icon related to the Component", notes = "return the png image related to the Component name parameter.")
    public void getComponentsImageRest(
            @PathVariable(value = "name") @ApiParam(name = "name", value = "Name of Component") String name,
            final HttpServletResponse response) {
        try {
            IOUtils.copy(getComponentPngImage(name), response.getOutputStream());
        } catch (IOException e) {
            throw new ComponentException(e);
        }
    }
}