package repository.interfaces;

import model.Entity;

import java.util.Optional;

/***
 * Interface for repositories that work with relationalDB
 * @param <ID> the id type of entity
 * @param <T> the entity to be operated with
 */
public interface ICrudRepository<ID,T extends Entity<ID>>{
    /***
     * Save the entity if is valid
     * @param entity t
     * @return {
     *     optional empty if it was saved
     *     entity if it was not valid
     * }
     */
    public abstract Optional<T> save(T entity);

    /***
     * @return Iterable with all the entities in DB
     */
    public abstract Iterable<T> findAll();

    /***
     * Return the entity if found
     * @param id ID
     * @return {
     *          optional with entity if found
     *          optional empty if not found
     *      }
     */
    public abstract Optional<T> find(ID id);

    /***
     * Delete an entity from DB
     * @param id ID
     * @return {
     *     optional with entity if deleted the entity
     *     optional empty if did not delete the entity
     * }
     */
    public abstract Optional<T> delete(ID id);

    /***
     * Update an entity from DB
     * @param entity T
     * @return {
     *     optional empty if updated
     *     optional with entity if not deleted
     * }
     */
    public abstract Optional<T> update(T entity);
}
