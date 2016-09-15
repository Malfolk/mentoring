package org.shop;

import org.shop.api.ProposalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ProposalService proposalService = (ProposalService) context.getBean("proposalService");
        proposalService.activateProposal(1L);
    }
}
