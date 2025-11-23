package com.ebank.dtos;

import com.ebank.entity.AccountEntity;
import com.ebank.entity.Address;
import com.ebank.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-22T22:02:32+0530",
    comments = "version: 1.6.0.Beta1, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setAddress( addressRequestToAddress( request.address() ) );
        userEntity.setEmail( request.email() );
        userEntity.setGender( request.gender() );
        userEntity.setMobilenumber( request.mobilenumber() );
        userEntity.setPassword( request.password() );
        userEntity.setUsername( request.username() );

        return userEntity;
    }

    @Override
    public UserResponse toResponse(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        String gender = null;
        String password = null;
        String mobilenumber = null;
        AddressResponse address = null;
        AccountNumberResponse account = null;

        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        gender = entity.getGender();
        password = entity.getPassword();
        mobilenumber = entity.getMobilenumber();
        address = addressToAddressResponse( entity.getAddress() );
        account = accountEntityToAccountNumberResponse( entity.getAccount() );

        UserResponse userResponse = new UserResponse( id, username, email, gender, password, mobilenumber, address, account );

        return userResponse;
    }

    @Override
    public void updateEntityFromRecord(UserRequest request, UserEntity entity) {
        if ( request == null ) {
            return;
        }

        if ( request.address() != null ) {
            if ( entity.getAddress() == null ) {
                entity.setAddress( new Address() );
            }
            addressRequestToAddress1( request.address(), entity.getAddress() );
        }
        if ( request.email() != null ) {
            entity.setEmail( request.email() );
        }
        if ( request.gender() != null ) {
            entity.setGender( request.gender() );
        }
        if ( request.mobilenumber() != null ) {
            entity.setMobilenumber( request.mobilenumber() );
        }
        if ( request.password() != null ) {
            entity.setPassword( request.password() );
        }
        if ( request.username() != null ) {
            entity.setUsername( request.username() );
        }
    }

    protected Address addressRequestToAddress(AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        Address address = new Address();

        address.setCity( addressRequest.city() );
        address.setCountry( addressRequest.country() );
        address.setPincode( addressRequest.pincode() );
        address.setStreet( addressRequest.street() );

        return address;
    }

    protected AddressResponse addressToAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        Long id = null;
        String country = null;
        String city = null;
        String street = null;
        String pincode = null;

        id = address.getId();
        country = address.getCountry();
        city = address.getCity();
        street = address.getStreet();
        pincode = address.getPincode();

        AddressResponse addressResponse = new AddressResponse( id, country, city, street, pincode );

        return addressResponse;
    }

    protected AccountNumberResponse accountEntityToAccountNumberResponse(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        Long id = null;
        String accountNumber = null;
        Double balance = null;

        id = accountEntity.getId();
        accountNumber = accountEntity.getAccountNumber();
        balance = accountEntity.getBalance();

        AccountNumberResponse accountNumberResponse = new AccountNumberResponse( id, accountNumber, balance );

        return accountNumberResponse;
    }

    protected void addressRequestToAddress1(AddressRequest addressRequest, Address mappingTarget) {
        if ( addressRequest == null ) {
            return;
        }

        if ( addressRequest.city() != null ) {
            mappingTarget.setCity( addressRequest.city() );
        }
        if ( addressRequest.country() != null ) {
            mappingTarget.setCountry( addressRequest.country() );
        }
        if ( addressRequest.pincode() != null ) {
            mappingTarget.setPincode( addressRequest.pincode() );
        }
        if ( addressRequest.street() != null ) {
            mappingTarget.setStreet( addressRequest.street() );
        }
    }
}
