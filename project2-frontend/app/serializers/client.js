import DS from 'ember-data';

export default DS.RESTSerializer.extend({
    primarykey: 'client_id',

    normalizeResponse(store, primaryModelClass, payload, id, resquestType) {
        console.log(payload);
        payload = {'client_id': payload};
        console.log(payload);
        return this._super(store, primaryModelClass, payload, id, requestType);

    }
});
