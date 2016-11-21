package io.lineage.example.helloworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * A set of utilities for Manipulating Character Arrays
 * 
 * @author Richard Clayton (Berico Technologies)
 * @date December 4, 2010
 */
public class CharacterVectorUtils
{

    /**
     * Get the union of characters from two strings, the set of characters are sorted alphabetically.
     * 
     * @param string1
     *            First String
     * @param string2
     *            Second String
     * @return the unique set of characters occurring in both strings
     */
    public static Collection<Character> union(final String string1, final String string2)
    {
        final Collection<Character> mergedVector = new TreeSet<Character>();
        mergedVector.addAll(stringToCharacterSet(string1));
        mergedVector.addAll(stringToCharacterSet(string2));
        return uniqueCharacters(mergedVector);
    }

    /**
     * Get the intersection of characters from two strings. The returned set is returned alphabetically.
     * 
     * @param string1
     *            First String
     * @param string2
     *            Second String
     * @return the set of characters that occur in both strings
     */
    public static Collection<Character> intersect(final String string1, final String string2)
    {
        final Collection<Character> vector1 = uniqueCharacters(stringToCharacterSet(string1));
        final Collection<Character> vector2 = uniqueCharacters(stringToCharacterSet(string2));
        final Collection<Character> intersectVector = new TreeSet<Character>();
        for (final Character c1 : vector1)
        {
            for (final Character c2 : vector2)
            {
                if (c1.equals(c2))
                {
                    intersectVector.add(c1);
                }
            }
        }
        return intersectVector;
    }

    /**
     * Sort a Character Set alphabetically
     * 
     * @param vector
     *            Input Character Array
     * @return Alphabetically sorted Character vector
     */
    public static Collection<Character> sortAlphabetically(final Collection<Character> vector)
    {
        return new TreeSet<Character>(vector);
    }

    /**
     * Sort a Character Array alphabetically
     * 
     * @param vector
     *            Input Character Vector
     * @return Alphabetically sorted Character vector
     */
    public static Collection<Character> sortAlphabetically(final Character[] vector)
    {
        final Collection<Character> sortedSet = new TreeSet<Character>();
        for (final Character c : vector)
        {
            sortedSet.add(c);
        }
        return sortedSet;
    }

    /**
     * Get the unique set of characters from a string.
     * 
     * @param vector
     *            input string vector
     * @return set of unique characters
     */
    public static Collection<Character> uniqueCharacters(final Collection<Character> vector)
    {
        final Collection<Character> uniqueSet = new HashSet<Character>();
        for (final Character c : vector)
        {
            if (!uniqueSet.contains(c))
            {
                uniqueSet.add(c);
            }
        }
        return uniqueSet;
    }

    /**
     * Convert a string to a set of characters
     * 
     * @param string
     *            input string
     * @return set of characters
     */
    public static Collection<Character> stringToCharacterSet(final String string)
    {
        final Collection<Character> charSet = new HashSet<Character>();
        for (final Character character : string.toCharArray())
        {
            charSet.add(character);
        }
        return charSet;
    }

    /**
     * Utility function to change a character array into a set.
     * 
     * @param charArray
     *            Input Character Array
     * @return Character Set composed of the Character Array
     */
    public static Collection<Character> characterArrayToSet(final Character[] charArray)
    {
        final Collection<Character> charSet = new HashSet<Character>();
        for (final Character c : charArray)
        {
            charSet.add(c);
        }
        return charSet;
    }

}
