package eu.corstjens.springbootwsexemple.webservices;

import eu.corstjens.schema.GetCountryRequest;
import eu.corstjens.schema.GetCountryResponse;
import eu.corstjens.springbootwsexemple.config.NAMESPACE;
import eu.corstjens.springbootwsexemple.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * CountryEndpoint the enpoint for the countries webservice implemented with jaxb2
 * Created by koencorstjens on 20/01/17.
 */


@Endpoint
public class CountryEndpoint {

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE.COUNTRIES_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}