/*
*
*    @ AH.GHORAB
*
*/
export abstract class BaseEntity<T> {
    constructor(
        public id?:T,
        public createdDate?: string,
        public updatedDate?: string,
    ) {
    }
}