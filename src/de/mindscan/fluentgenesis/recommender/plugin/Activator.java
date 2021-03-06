/**
 * 
 * MIT License
 *
 * Copyright (c) 2020 Maxim Gansert, Mindscan
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package de.mindscan.fluentgenesis.recommender.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 
 */
public class Activator implements BundleActivator {

    private static BundleContext context;

    // TODO: Actually this doesn't belong here, anyways, can be transferred later
    private static Image defaultImage;

    /**
     * @return the context
     */
    public static BundleContext getContext() {
        return context;
    }

    public static Image getDefaultImage() {
        if (defaultImage == null) {
            ImageDescriptor imageDescriptorFromPlugin = AbstractUIPlugin.imageDescriptorFromPlugin( "FluentGenesis-Plugin", "hashi_16.gif" );
            defaultImage = imageDescriptorFromPlugin.createImage();
        }

        return defaultImage;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void start( BundleContext context ) throws Exception {
        Activator.context = context;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void stop( BundleContext context ) throws Exception {
        Activator.context = null;
    }

}
