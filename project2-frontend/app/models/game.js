import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    price: DS.attr('string'),
    photo: DS.attr('string'),
    copies: DS.attr('number')
});