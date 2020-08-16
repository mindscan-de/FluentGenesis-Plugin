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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import de.mindscan.fluentgenesis.recommender.backend.RestRequestService;
import de.mindscan.fluentgenesis.recommender.plugin.Activator;

/**
 * 
 */
public class ProposalProcessor implements IContentAssistProcessor {

    private static final ICompletionProposal[] EMPTY_PROPOSALS = new ICompletionProposal[0];
    private static final IContextInformation[] EMPTY_CONTEXTS = new IContextInformation[0];

    private static final String UNHELPFUL_ERROR_MESSAGE = "The proposal computer got an error during code completion.";

    private RestRequestService predictionService = new RestRequestService();

    /** 
     * {@inheritDoc}
     */
    @Override
    public ICompletionProposal[] computeCompletionProposals( ITextViewer viewer, int offset ) {
        try {
            List<String> methodNames = predictionService.requestMethodNamePredictions();

            ArrayList<CompletionProposal> proposals = new ArrayList<>();

            int counter = 0;
            for (String name : methodNames) {
                String newContent = "/* " + name + " */";
                proposals.add( new CompletionProposal( newContent, offset, 0, newContent.length(), Activator.getDefaultImage(),
                                "methodname[" + counter + "]: " + name, null, "This is a predicted method name of the FluenetGenesis-Project." ) );
                counter++;
            }

            return proposals.toArray( new ICompletionProposal[proposals.size()] );
        }
        catch (Exception ex) {
            return EMPTY_PROPOSALS;
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public IContextInformation[] computeContextInformation( ITextViewer viewer, int offset ) {
        return EMPTY_CONTEXTS;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return null;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String getErrorMessage() {
        return UNHELPFUL_ERROR_MESSAGE;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

}
