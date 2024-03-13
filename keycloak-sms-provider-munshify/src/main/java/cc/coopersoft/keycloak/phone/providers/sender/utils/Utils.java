package cc.coopersoft.keycloak.phone.providers.sender.utils;

import java.util.Map;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class Utils {
    
	
    public static String convertTemplateToBody(String templateString, Map<String, String> valuesMap) {
	     
	// Define a pattern to match placeholders in the templateString
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        
        // Create a matcher for the templateString
        Matcher matcher = pattern.matcher(templateString);
        
        // Create a StringBuilder to store the replaced string
        StringBuilder result = new StringBuilder();
        
        // Iterate through the matches
        int lastEnd = 0;
        while (matcher.find()) {
            // Append the part of the templateString before the placeholder
            result.append(templateString, lastEnd, matcher.start());
            
            // Get the placeholder key
            String key = matcher.group(1);
            
            // Look up the value for the key in the valuesMap
            String value = valuesMap.get(key);
            
            // If the value is found, append it; otherwise, keep the placeholder
            if (value != null) {
                result.append(value);
            } else {
                result.append(matcher.group());
            }
            
            // Update the lastEnd position
            lastEnd = matcher.end();
        }
        
        // Append the remaining part of the templateString after the last placeholder
        result.append(templateString.substring(lastEnd));
        
        // Convert the StringBuilder to a String and return it
        return result.toString();
	    
    }

}
