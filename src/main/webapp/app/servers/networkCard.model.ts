/*
*
*    @ AH.GHORAB
*
*/
import { BaseEntity } from '../shared/baseEntity/baseEntity.model';

export class NetworkCard extends BaseEntity<number>{
	constructor(
		public name?: string,
		public ipAddress?: string,
		public macAddress?: string,
		public isPrimary?: boolean
	) {
		super();
		this.name = '';
		this.ipAddress = '';
		this.macAddress = '';
		this.isPrimary = false;
		this.id = null;
	}
}