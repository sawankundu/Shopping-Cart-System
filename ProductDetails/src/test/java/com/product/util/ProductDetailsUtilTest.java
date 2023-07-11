package com.product.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.junit.Assert;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ProductDetailsUtilTest {
	private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * This method used to convert json to Model Object
     *
     * @param basePath
     * @param type
     * @return Model class
     */
    public static <T> T jsonToModel(String basePath, Class<T> type) {
        try {
            File file = new ClassPathResource(basePath).getFile();
            String stringData = new String(Files.readAllBytes(file.toPath()));
            MAPPER.registerModule(new JavaTimeModule());
            MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return MAPPER.readValue(stringData, type);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("UNABLE_TO_READ_JSON_FILE");
        }
        return null;
    }

    /**
     * This method used to convert soap message xml to Model Object
     *
     * @param basePath
     * @param type
     * @return Model class
     */
    public static <T> T xmlToModel(String basePath, Class<T> type) throws IOException, SOAPException {

        try {
            SOAPMessage message = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage(null,
                    new ClassPathResource(basePath).getInputStream());
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return type.cast(jaxbUnmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument()));

        } catch (JAXBException e) {
            Assert.fail("UNABLE_TO_READ_SOAP_XML_FILE");
        }
        return null;
    }


}
