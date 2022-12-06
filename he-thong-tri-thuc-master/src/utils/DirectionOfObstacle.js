import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class DirectionOfObstacle extends MemberShipFunction {
  constructor() {
    super([
      new Trap('NM', -9999, -9999, -60, -30),
      new Trimf('NS', -60, -30, 0),
      new Trimf('Z', -30, 0, 30),
      new Trimf('PS', 0, 30, 60),
      new Trap('PM', 30, 60, 9999, 9999),
    ]);
  }
}
export default DirectionOfObstacle;
