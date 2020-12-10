package com.example.demo.util;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SpringBeanUtil {

	// Merge not null is used for ensuring that we dont get an error when we do our
	// update statement
	// custom error handler

	// constructor that takes the data source in and data target
	public static void mergeNotNull(Object source, Object target) {
		copyProperties(source, target, getNullPropertyName(source));
	}

	private static String[] getNullPropertyName(Object source) {
		final BeanWrapper wrappedSourceObject = new BeanWrapperImpl(source);
		// loop through our data
		Set<String> propertyNames = new HashSet<>();
		for (PropertyDescriptor propertyDescriptor : wrappedSourceObject.getPropertyDescriptors()) {
			if (wrappedSourceObject.getPropertyValue(propertyDescriptor.getName()) == null)
				propertyNames.add(propertyDescriptor.getName());
		}

		return propertyNames.toArray(new String[propertyNames.size()]);
	}

	// just checks our object is not null during merge and prevents spring
	// breaking

}
