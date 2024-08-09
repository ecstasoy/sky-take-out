package com.sky.service;

import com.sky.entity.AddressBook;
import java.util.List;

public interface AddressBookService {
    /**
     * Query address book list
     *
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * Add address
     *
     * @param addressBook
     */
    void save(AddressBook addressBook);

    /**
     * Query address by id
     *
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * Update address by id
     *
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * Set default address
     *
     * @param addressBook
     */
    void setDefault(AddressBook addressBook);

    /**
     * Delete address by id
     *
     * @param id
     */
    void deleteById(Long id);

}
