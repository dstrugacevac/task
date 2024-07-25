package ingemark.assignment.task.shared.common.infrastructure.error.specs;

import org.springframework.http.HttpStatus;

public enum ErrorSpecification {

    // Product
    PRODUCT_DEFINITION_NOT_VALID("ProductDefinitionNotValid", "Product"),

    PRODUCT_NOT_SAVED("ProductNotSaved", "Product"),
    PRODUCT_ALREADY_EXISTS("ProductAlreadyExists", "Product", HttpStatus.CONFLICT),
    PRODUCT_NOT_FOUND("ProductNotFound", "Product", HttpStatus.NOT_FOUND),
    CODE_VALUE_NOT_VALID("CodeValueNotValid", "Product"),
    PRICE_NOT_VALID("PriceNotValid", "Product"),
    IS_AVAILABLE_NOT_VALID("IsAvailableNotValid", "Product"),


    // Shared - domain
    ID_NOT_VALID("IdNotValid", "Common"),
    NAME_NOT_VALID("NameNotValid", "Common"),
    PRIVILEGE_NOT_FOUND("PrivilegeNotFound", "Privilege", HttpStatus.NOT_FOUND),

    ALREADY_EXIST("AlreadyExists", "AlreadyExists", HttpStatus.CONFLICT),
    DOMAIN_EVENT_NOT_VALID("DomainEventNotValid", "DomainEventNotValid"),
    RESPONSE_MAPPING_EXCEPTION("ResponseMappingException", "Response"),
    LAST_CHANGE_DATE_NOT_VALID("LastChangeDateNotValid", "LastChange"),
    FLOOR_NOT_VALID("FloorNotValid", "Common"),
    SQUARE_AREA_NOT_VALID("SquareAreaNotValid", "Common"),
    SNAPSHOT_READ_EXCEPTION("SnapshotReadException", "Snapshot"),
    SNAPSHOT_WRITE_EXCEPTION("SnapshotReadException", "Snapshot"),
    EXPRESSION_NOT_VALID("ExpressionNotValid", "Filter"),
    SORT_NOT_VALID("SortNotValid", "Common"),
    DOMAIN_EVENT_PUBLISHER_NOT_REGISTERED("DomainEventPublisherNoRegistered", "DomainEvent"),
    XML_PARSER_EXCEPTION("XMLParserException", "POI"),
    BAD_CREDENTIALS_ERROR("invalidCredentials", "invalidCredentials"),
    UNKNOWN("notDefined", "notDefined");


    String code;
    String target;
    HttpStatus httpStatus;

    ErrorSpecification(String code, String target) {
        this(code, target, HttpStatus.BAD_REQUEST);
    }

    ErrorSpecification(String code, String target, HttpStatus httpStatus) {
        this.code = code;
        this.target = target;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getTarget() {
        return target;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
