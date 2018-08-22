import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Route | sell-game', function(hooks) {
  setupTest(hooks);

  test('it exists', function(assert) {
    let route = this.owner.lookup('route:sell-game');
    assert.ok(route);
  });
});
