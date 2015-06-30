package com.example.infoparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;

	public static Nitu GetterSetter = null;

	public static Nitu getGetterSetter() {
		return GetterSetter;
	}

	/**
	 * Called when tag starts ( ex:- <name>AndroidPeople</name> -- <name> )
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = true;

		if (localName.equals("PlaceSearchResponse")) {
			/** Start */
			GetterSetter = new Nitu();

		}
	}

	/**
	 * Called when tag closing ( ex:- <name>AndroidPeople</name> -- </name> )
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		currentElement = false;

		/** set value */
		//
		if (localName.equals("name")) {

			GetterSetter.setName(currentValue);

			currentValue = "";
		} else if (localName.equalsIgnoreCase("vicinity")) {

			GetterSetter.setVicinity(currentValue);

			currentValue = "";
		}
		else if (localName.equalsIgnoreCase("lng")) {

			GetterSetter.setLng(currentValue);

			currentValue = "";
		}
		else if (localName.equalsIgnoreCase("lat")) {

			GetterSetter.setLat(currentValue);

			currentValue = "";
		}
		
		

	}

	/**
	 * Called to get tag characters ( ex:- <name>AndroidPeople</name> -- to get
	 * AndroidPeople Character )
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
		}

	}

}
