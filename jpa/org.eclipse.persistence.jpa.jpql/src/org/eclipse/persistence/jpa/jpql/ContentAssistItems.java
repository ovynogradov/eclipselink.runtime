/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.persistence.jpa.jpql;

import org.eclipse.persistence.jpa.jpql.spi.IEntity;
import org.eclipse.persistence.jpa.jpql.spi.IMapping;

/**
 * This object stores the various choices available for content assist for a certain position within
 * a JPQL query. The choices are stored in categories (abstract schema types, identifiers,
 * identification variables and mappings).
 *
 * @version 2.3
 * @since 2.3
 * @author Pascal Filion
 */
public interface ContentAssistItems {

	/**
	 * Returns the collection of possible abstract schema types.
	 *
	 * @return The {@link IEntity entities} defined in the persistence context
	 */
	Iterable<IEntity> abstractSchemaTypes();

	/**
	 * Creates a new JPQL query by inserting the given choice at the given position. The resulted
	 * JPQL query and position will be adjusted by converting the escaped characters escaped to
	 * string values, which means '\r' will have been converted to '\\r.
	 * <p>
	 * TODO: TO REWORD: If the choice is has more than one identifier, for instance, <code>IS NOT
	 * NULL</code>, then the replacement will go further than just the current word. If the cursor
	 * is in "... IS NOT N|" and the choice is "IS NOT NULL", then "IS NOT" will not be added twice.
	 *
	 * @param jpqlQuery The JPQL query to insert the given choice
	 * @param choice The choice to insert into the query
	 * @param position The position of insertion
	 * @param insert Flag that determines if the partial word following the cursor should be left
	 * intact or replaced by the choice
	 * @return The result of inserting the choice into the query and the new position within that query
	 */
	ResultQuery buildEscapedQuery(String jpqlQuery, String choice, int position, boolean insert);

	/**
	 * Creates a new JPQL query by inserting the given choice at the given position.
	 * <p>
	 * TODO: TO REWORD: If the choice is has more than one identifier, for instance, <code>IS NOT
	 * NULL</code>, then the replacement will go further than just the current word. If the cursor
	 * is in "... IS NOT N|" and the choice is "IS NOT NULL", then "IS NOT" will not be added twice.
	 *
	 * @param jpqlQuery The JPQL query to insert the given choice
	 * @param choice The choice to insert into the query
	 * @param position The position of insertion
	 * @param insert Flag that determines if the partial word following the cursor should be left
	 * intact or replaced by the choice
	 * @return The result of inserting the choice into the query and the new position within that query
	 */
	ResultQuery buildQuery(String jpqlQuery, String choice, int position, boolean insert);

	/**
	 * Retrieves the abstract schema type that is mapped with the given identification variable.
	 *
	 * @param identificationVariable The identification variable that, if defined as a range variable,
	 * will be mapped to a managed type
	 * @return The abstract schema type mapped with the given identification variable or
	 * <code>null</code> if the given variable is mapped to something else or not mapped to anything
	 */
	IEntity getAbstractSchemaType(String identificationVariable);

	/**
	 * Determines whether this holder has at least one item.
	 *
	 * @return <code>true</code> if there is at least one item;
	 * <code>false</code> if this holder is empty
	 */
	boolean hasItems();

	/**
	 * Returns the collection of possible identification variables.
	 *
	 * @return The list of possible identification variables
	 */
	Iterable<String> identificationVariables();

	/**
	 * Returns the collection of possible JPQL identifiers.
	 *
	 * @return The list of possible JPQL identifiers
	 */
	Iterable<String> identifiers();

	/**
	 * Returns the collection of possible {@link IMapping mappings}, which can be state fields,
	 * association fields and/or collection fields depending on the location used to retrieve the
	 * possible choices.
	 *
	 * @return The list of possible choices {@link IMapping mappings}
	 */
	Iterable<IMapping> mappings();
}