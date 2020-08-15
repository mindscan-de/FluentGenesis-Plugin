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
package de.mindscan.fluentgenesis.recommender.proposal;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * 
 */
public class FluentGenesisCodeCompletionProposal implements ICompletionProposal {

    /** 
     * {@inheritDoc}
     */
    @Override
    public void apply( IDocument document ) {
        // TODO: here goes the code for the application of the proposal
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Point getSelection( IDocument document ) {
        // intentionally left the default implementation.
        return null;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String getAdditionalProposalInfo() {
        return "fluentgenesis-code-completion-example: this are additional information here";
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String getDisplayString() {
        return "fluentgenesis-code-comletion_stubname";
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        // intentionally left blank here right now
        // hashi_64.gif but how...
        return null;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public IContextInformation getContextInformation() {
        // intentionally left blank here right now
        return null;
    }

}
