package eu.corstjens.springbootwsexemple.webservices;

import eu.corstjens.springbootwsexemple.config.NAMESPACE;
import eu.corstjens.springbootwsexemple.model.User;
import eu.corstjens.springbootwsexemple.services.UserService;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * UserEndpoint the enpoint for the users webservice implemented with JDOM
 * Created by koencorstjens on 20/01/17.
 */
@Endpoint
public class UserEndpoint {


    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE.USERS_URI, localPart = "getUserByIDRequest")
    @ResponsePayload
    public Element getUserByID(@RequestPayload Element incoming) {

        Element userIdElement = incoming.getChild("userId");
        String id = userIdElement.getText();
        User found = userService.findById(id);

        Element outgoing = new Element("getUserByIDResponse");
        outgoing.addNamespaceDeclaration(Namespace.getNamespace("tns", NAMESPACE.USERS_URI));

        Element user = new Element("user");
        user.addContent(new Element("userId").setText(found.getUserId()));
        user.addContent(new Element("firstName").setText(found.getFirstName()));
        user.addContent(new Element("lastName").setText(found.getLastName()));
        user.addContent(new Element("email").setText(found.getEmail()));
        user.addContent(new Element("gsm").setText(found.getGsm()));

        outgoing.addContent(user);
        return outgoing;
    }
}
