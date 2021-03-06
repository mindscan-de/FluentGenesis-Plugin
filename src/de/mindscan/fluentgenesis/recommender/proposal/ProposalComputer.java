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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

/**
 * 
 */
public class ProposalComputer implements IJavaCompletionProposalComputer {

    private final ProposalProcessor proposalProcessor = new ProposalProcessor();

    /** 
     * {@inheritDoc}
     */
    @Override
    public void sessionStarted() {
        // intentionally left blank
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<ICompletionProposal> computeCompletionProposals( ContentAssistInvocationContext context, IProgressMonitor monitor ) {
        return Arrays.asList( proposalProcessor.computeCompletionProposals( context.getViewer(), context.getInvocationOffset() ) );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<IContextInformation> computeContextInformation( ContentAssistInvocationContext context, IProgressMonitor monitor ) {
        return Arrays.asList( proposalProcessor.computeContextInformation( context.getViewer(), context.getInvocationOffset() ) );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String getErrorMessage() {
        return proposalProcessor.getErrorMessage();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void sessionEnded() {
        // intentionally left blank
    }

}
